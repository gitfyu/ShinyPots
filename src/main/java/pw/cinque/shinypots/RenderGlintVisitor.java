package pw.cinque.shinypots;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class RenderGlintVisitor extends MethodVisitor {

	public RenderGlintVisitor(MethodVisitor mv) {
		super(Opcodes.ASM4, mv);
	}

	@Override
	public void visitIntInsn(int opcode, int operand) {
		if (opcode == Opcodes.SIPUSH && operand == 772) {
			operand = 773; // This adds the 'shiny pots'
		}
		
		super.visitIntInsn(opcode, operand);
	}

}
