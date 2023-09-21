package vm;

import vm.asm.VM;
import static vm.asm.Bytecode.*;

public class Main {

   static final int[] add = {
     CONST.ordinal(), 2,
     CONST.ordinal(), 3,
     ADD.ordinal(),
     HALT.ordinal()
   };

    public static void main(String[] args) throws Exception {
        VM vm = new VM(add, 0,0);
        vm.trace = true;
        vm.exec();

    }
}