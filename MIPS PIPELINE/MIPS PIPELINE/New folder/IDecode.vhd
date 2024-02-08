----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/29/2023 06:15:45 PM
-- Design Name: 
-- Module Name: ID - Behavioral
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

entity ID is
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
end ID;

architecture Behavioral of ID is

-- RegFile
  type regFile is array (0 to 15) of STD_LOGIC_VECTOR (15 DOWNTO 0);
  signal ROM : regFile := ( others=>X"0000" );
begin 
	

process (clk)
begin
  if falling_edge(clk) then
      if(en = '1' and regWrite = '1') then
          ROM(conv_integer(WA)) <= WD;
      end if;
  end if;
end process;

  RD1  <= ROM(conv_integer(instr(12 downto 10)));
  RD2  <= ROM(conv_integer(instr(9 downto 7)));

process(instr, extOp)
begin
  if(extOp = '1') then
      ext_imm(15 downto 7) <= instr(6) & instr(6) & instr(6) & instr(6) & instr(6) & instr(6) & instr(6) & instr(6) & instr(6) ;
  else 
      ext_imm(15 downto 7) <= "000000000";
   end if; 
  ext_imm(6 downto 0) <= instr(6 downto 0);
end process;

  func <= instr(2 downto 0);
  sa <= instr(3);

end Behavioral;
