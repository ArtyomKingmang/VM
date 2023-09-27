package examples;

import vm.asm.VM;
import java.util.Scanner;
import static vm.asm.Bytecode.*;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first: ");
        int num1 = scanner.nextInt();
        System.out.println("Enter second: ");
        int num2 = scanner.nextInt();
        System.out.println("Enter operation: ");
        String operation = scanner.next();
        int opcode;
        if(operation.equals("+")){
            opcode = ADD.ordinal();

        }else if(operation.equals("-")){
            opcode = SUB.ordinal();

        }else if(operation.equals("*")){
            opcode = MUL.ordinal();

        }else if(operation.equals("/")){
            opcode = DIV.ordinal();
        }else {
            System.out.println("!");
            return;
        }

        int[] instr = {
                CONST.ordinal(), num1,
                CONST.ordinal(), num2,
                opcode,
                PRINT.ordinal(),
                HALT.ordinal(),
        };


        VM vm = new VM(instr,0,0);
        vm.trace = true;
        //vm.exec();

    }
}
