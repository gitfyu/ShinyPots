package pw.cinque.shinypots;

import javax.swing.JOptionPane;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class RenderItemTransformer implements IClassTransformer {

	@Override
	public byte[] transform(String name, String transformedName, byte[] buffer) {
		if (transformedName.equals("net.minecraft.client.renderer.entity.RenderItem")) {
			ClassReader reader = new ClassReader(buffer);
			ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
			ClassVisitor visitor = new ClassVisitor(Opcodes.ASM5, writer) {
				
				@Override
				public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
					MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
					
					if (desc.equals("(IIIII)V"))  {
						return new RenderGlintVisitor(mv);
					}
					
					return mv;
				}
				
			};
			
			reader.accept(visitor, 0);
			return writer.toByteArray();
		}
		
		return buffer;
	}

}
