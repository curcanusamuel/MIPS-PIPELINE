----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/29/2023 05:53:59 PM
-- Design Name: 
-- Module Name: IFetch - Behavioral
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

entity IFetch is
    Port (clk: in STD_LOGIC;
          rst : in STD_LOGIC;
          en : in STD_LOGIC;
          BranchAddress : in STD_LOGIC_VECTOR(15 downto 0);
          JumpAddress : in STD_LOGIC_VECTOR(15 downto 0);
          Jump : in STD_LOGIC;
          PCSrc : in STD_LOGIC;
          Instruction : out STD_LOGIC_VECTOR(15 downto 0);
          PCinc : out STD_LOGIC_VECTOR(15 downto 0));
end IFetch;

architecture Behavioral of IFetch is
type tROM is array (0 to 255) of STD_LOGIC_VECTOR (15 downto 0);
signal ROM : tROM := (

--suma primelor 8 numere pare incepand cu 10
    
    
    
   		  B"101_000_001_0001010",        --addi $1,$0,10
          B"101_000_100_0001000",        --addi $4,$0,8
          B"101_000_010_0000000",        --addi $2,$0,0 
          B"101_000_011_0000000",        --addi $3,$0,0
          B"000_000_000_000_0_010",      --NoOP
          B"000_010_010_001_0_010",      --add $1,$2,$2
          B"000_000_000_000_0_010",		 --NoOp
          B"000_000_000_000_0_010",		 --NoOp
          B"101_011_011_0000001",        --addi $3,$3,1
          B"101_001_001_0000010",        --addi $1,$1,2
          B"011_100_011_0000010",        --beq $3,$4,2 
          B"100_0000000000100",          --jmp 4 
          B"000_000_000_000_0_010",		 --NoOp
	
	
			 
	
    others => X"0000");


signal PC : STD_LOGIC_VECTOR(15 downto 0) := (others => '0');
signal PCAux, NextAddr, BranchAux: STD_LOGIC_VECTOR(15 downto 0);

begin
    process(clk)
    begin
        if rising_edge(clk) then
            if rst = '1' then
                PC <= (others => '0');
            elsif en = '1' then
                PC <= NextAddr;
            end if;
        end if;
    end process;
    Instruction <= ROM(conv_integer(PC(7 downto 0)));
    PCAux <= PC + 1;
    PCinc <= PCAux;
    
    process(PCSrc, PCAux, BranchAddress)
    begin
        if PCSrc = '1' then
           BranchAux <= BranchAddress;
        else
            BranchAux <= PCAux;
        end if;
    end process;

    process(Jump, BranchAux, JumpAddress)
    begin
        if Jump = '1' then
            NextAddr <= JumpAddress;
        else
            NextAddr <= BranchAux;
        end if;
    end process;

end Behavioral;
