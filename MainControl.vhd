----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/29/2023 05:24:37 PM
-- Design Name: 
-- Module Name: MAIN_CONTROL - Behavioral
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

entity MAIN_CONTROL is
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
end MAIN_CONTROL;

architecture Behavioral of MAIN_CONTROL is
begin

 process(Instr)
   begin
       RegDst <= '0'; ExtOp <= '0'; ALUSrc <= '0'; 
       Branch <= '0'; Jump <= '0'; MemWrite <= '0';
       MemtoReg <= '0'; RegWrite <= '0';
       ALUOp <= "000";
       case (Instr) is
           when "100" => -- Jump
               Jump <= '1'; 
          when "101" => -- ADDI
    		 ExtOp <= '1';
   			 ALUSrc <= '1';
   			 RegWrite <= '1';
    		 ALUOp <= "001";

           when "111" => -- LW
               ExtOp <= '1';
                ALUSrc <= '1';
                RegWrite <= '1';
                ALUOp <= "001";
           when "010" => -- SW
               ExtOp <= '1';
               ALUSrc <= '1';
               MemWrite <= '1';
               ALUOp <= "001";
           when "011" => -- BEQ              
                ExtOp <= '1';
                Branch <= '1';
                ALUOp <= "010";
           when "001" => -- LI
               ALUSrc <= '1';
               RegWrite <= '1';
               ALUOp <= "001";
           when "110" => -- BNE
               ExtOp <= '1';
               Branch <= '1';
               ALUOp <= "010";
           when "000" => -- R type
               RegDst <= '1';
               RegWrite <= '1';
               ALUOp <= "000";
           when others => 
               RegDst <= '0'; ExtOp <= '0'; ALUSrc <= '0'; 
               Branch <= '0'; Jump <= '0'; MemWrite <= '0';
               MemtoReg <= '0'; RegWrite <= '0';
               ALUOp <= "000";
       end case;
   end process;
		

end Behavioral;