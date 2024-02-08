----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/29/2023 07:23:30 PM
-- Design Name: 
-- Module Name: test_env - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------



library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity test_env is
    Port ( clk : in STD_LOGIC;
           btn : in STD_LOGIC_VECTOR (4 downto 0);
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           led : out STD_LOGIC_VECTOR (15 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0));
end test_env;
architecture Behavioral of test_env is

component MPG is
      Port ( en : out STD_LOGIC;
       input : in STD_LOGIC;
       clock : in STD_LOGIC);
end component;

component SSD is
    Port ( clk: in STD_LOGIC;
           digits: in STD_LOGIC_VECTOR(15 downto 0);
           an: out STD_LOGIC_VECTOR(3 downto 0);
           cat: out STD_LOGIC_VECTOR(6 downto 0));
end component;


component IFetch is
    Port (clk: in STD_LOGIC;
          rst : in STD_LOGIC;
          en : in STD_LOGIC;
          BranchAddress : in STD_LOGIC_VECTOR(15 downto 0);
          JumpAddress : in STD_LOGIC_VECTOR(15 downto 0);
          Jump : in STD_LOGIC;
          PCSrc : in STD_LOGIC;
          Instruction : out STD_LOGIC_VECTOR(15 downto 0);
          PCinc : out STD_LOGIC_VECTOR(15 downto 0));
end component;


component ID is
    Port (
	 regWrite: in std_logic;
	 regDest: in std_logic;
    instr: in std_logic_vector(12 downto 0);
    clk: in std_logic;
    en: in std_logic;
    extOp: in std_logic;
    WD: in std_logic_vector(15 downto 0);
    RD1,RD2, Ext_Imm: out std_logic_vector(15 downto 0);
    func: out std_logic_vector(2 downto 0);
    sa: out std_logic;
	WA:in std_logic_vector(2 downto 0)
    );
end component;

component MAIN_CONTROL is
    Port ( Instr : in STD_LOGIC_VECTOR(2 downto 0);
           RegDst : out STD_LOGIC;
           ExtOp : out STD_LOGIC;
           ALUSrc : out STD_LOGIC;
           Branch : out STD_LOGIC;
           Jump : out STD_LOGIC;
           ALUOp : out STD_LOGIC_VECTOR(2 downto 0);
           MemWrite : out STD_LOGIC;
           MemtoReg : out STD_LOGIC;
           RegWrite : out STD_LOGIC);
end component;

component EX is
     Port( 
	PCinc : in STD_LOGIC_VECTOR(15 downto 0);
           RD1 : in STD_LOGIC_VECTOR(15 downto 0);
           RD2 : in STD_LOGIC_VECTOR(15 downto 0);
           Ext_Imm : in STD_LOGIC_VECTOR(15 downto 0);
           func : in STD_LOGIC_VECTOR(2 downto 0);
           sa : in STD_LOGIC;
           ALUSrc : in STD_LOGIC;
           ALUOp : in STD_LOGIC_VECTOR(2 downto 0);
           BA : out STD_LOGIC_VECTOR(15 downto 0);
           ALURes : out STD_LOGIC_VECTOR(15 downto 0);
           Zero : out STD_LOGIC;
		   RegDest:	in std_logic;
		   rd,rt:in std_logic_vector(2 downto 0);	
		   wa:out  std_logic_vector(2 downto 0));
end component;

component memoryData is
    port ( clk : in STD_LOGIC;
           en : in STD_LOGIC;
           ALUResIn : in STD_LOGIC_VECTOR(15 downto 0);
           RD2 : in STD_LOGIC_VECTOR(15 downto 0);
           MemWrite : in STD_LOGIC;			
           MemData : out STD_LOGIC_VECTOR(15 downto 0);
           ALUResOut : out STD_LOGIC_VECTOR(15 downto 0);
		   PcSRc:out std_logic;
		   zeroDetect,branch:in std_logic
		   
		   );
end component;

signal func : STD_LOGIC_VECTOR(2 downto 0);
signal sa, zeroDetect : STD_LOGIC;
signal digits : STD_LOGIC_VECTOR(15 downto 0);
signal en, rst, PCSrc : STD_LOGIC; 
signal RegDst, ExtOp, ALUSrc, Branch, Jump, MemWrite, MemtoReg, RegWrite : STD_LOGIC;
signal ALUOp :  STD_LOGIC_VECTOR(2 downto 0);
signal Instr, PCinc, RD1, RD2, WD, Ext_imm : STD_LOGIC_VECTOR(15 downto 0); 
signal JA, BA, ALURes, ALURes1, MemData : STD_LOGIC_VECTOR(15 downto 0);
signal rt,rd,wa: std_logic_vector(2 downto 0);


signal IF_ID : STD_LOGIC_VECTOR(31 downto 0);
signal ID_EX : STD_LOGIC_VECTOR(82 downto 0);
signal EX_MEM : STD_LOGIC_VECTOR(56 downto 0);
signal MEM_WB : STD_LOGIC_VECTOR(36 downto 0);

begin

    
    m1: MPG port map(en, btn(0), clk);
    m2: MPG port map(rst, btn(1), clk);
    C1: IFetch port map(clk, rst, en, EX_MEM(51 downto 36), JA, Jump, PCSrc,  Instr , Pcinc);
    c2: ID port map(MEM_WB(35),ID_EX(73), Instr(12 downto 0), clk, en, extOp, WD, RD1, RD2,ext_imm ,func, sa,WA);
    c3: Main_Control port map(Instr(15 downto 13), RegDst, ExtOp, ALUSrc, Branch, Jump, ALUOp, MemWrite, ID_EX(81), RegWrite);
    c4: Ex port map(PCinc, ID_EX(56 downto 41), ID_EX(40 downto 25),ID_EX(24 downto 9),  ID_EX(8 downto 6), id_ex(82), ID_EX(74), ID_EX(77 downto 75), BA, ALURes, ZeroDetect,id_ex(73),IF_ID(6 downto 4),IF_ID(9 downto 7),wa); 				  		   
    c5: memoryData port map(clk, en, EX_MEM(34 downto 19),  EX_MEM(18 downto 3),EX_MEM(53), MemData, ALURes1,Pcsrc,EX_MEM(56),EX_MEM(52));
    
    
    process(clk)
        begin     
            
            --IF/ID
                IF_ID(31 downto 16) <= PCinc;
                IF_ID(15 downto 0) <= Instr;            
                
            --ID/EX
                ID_EX(82)<=sa;    
                ID_EX(81) <= MemtoReg;
                ID_EX(80) <= RegWrite;
                ID_EX(79) <= MemWrite;
                ID_EX(78) <= Branch;
                ID_EX(77 downto 73) <= ALUOp & ALUSrc & RegDst;
                ID_EX(72 downto 57) <= IF_ID(31 downto 16);
                ID_EX(56 downto 41) <= RD1;
                ID_EX(40 downto 25) <= RD2;
                ID_EX(24 downto 9) <= Ext_imm;
                ID_EX(8 downto 6) <= func;
                ID_EX(5 downto 3) <= IF_ID(6 downto 4);
                ID_EX(2 downto 0) <= IF_ID(9 downto 7) ;
            --EX/MEM 
                EX_MEM(56)<=zeroDetect;   
                EX_MEM(55 downto 54) <= ID_EX(81 downto 80);
                EX_MEM(53 downto 52) <= ID_EX(79 downto 78);
                EX_MEM(51 downto 36) <= BA;
                EX_MEM(35) <= '0';
                EX_MEM(34 downto 19) <= ALURes;
                EX_MEM(18 downto 3) <= ID_EX(40 downto 25);
                EX_MEM(2 downto 0)<=WA;
                
            --MEM/WB    
                MEM_WB(36 downto 35) <= EX_MEM(55 downto 54);
                MEM_WB(34 downto 19) <= EX_MEM(34 downto 19);
                MEM_WB(18 downto 3) <= EX_MEM(18 downto 3);
                MEM_WB(2 downto 0) <= EX_MEM(2 downto 0);
        end process;
    
   with MEM_WB(36) select
        WD <= MemData when '1',
               MEM_WB(34 downto 19) when '0',
            (others => 'X') when others;
            
            
    --jump adress
    JA <= PCinc (15 downto 13) & instr(12 downto 0);
    
     
     process(sw, Instr, PCinc, RD1, RD2, Ext_Imm, ALURes, MemData, WD)
     begin
       case sw(7 downto 5) is
         when "000" =>
           digits <= Instr;
         when "001" =>
           digits <= PCinc;
         when "010" =>
           digits <= RD1;
         when "011" =>
           digits <= RD2;
         when "100" =>
           digits <= Ext_Imm;
         when "101" =>
           digits <= ALURes;
         when "110" =>
           digits <= MemData;
         when "111" =>
           digits <= WD;
         when others =>
           digits <= (others => '0');
       end case;
     end process;
    S1 : SSD port map (clk, digits, an, cat);
    led(10 downto 0) <= ALUOp & RegDst & ExtOp & ALUSrc & Branch & Jump & MemWrite & MemtoReg & RegWrite;

end Behavioral;
