package org.zhq;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class PrintClassMethod {
    public static void main(String[] args) throws IOException {
        URL resource = PrintClassMethod.class.getResource("/Demo.class");
        String url = resource.getFile();
        FileInputStream fis = new FileInputStream(url);
        ClassReader cr = new ClassReader(fis);
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
//        ClassVisitor cvis = new ClassVisitor(Opcodes.ASM5, cw) {
//            @Override
//            public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
//                System.out.printf("field:%s%n", name);
//                return super.visitField(access, name, desc, signature, value);
//            }
//
//            @Override
//            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//                System.out.printf("method:%s%n", name);
//                return super.visitMethod(access, name, desc, signature, exceptions);
//            }
//        };
        ClassNode cn = new ClassNode(Opcodes.ASM5);
        cr.accept(cn,ClassReader.SKIP_DEBUG);
        for (MethodNode method : (List<MethodNode>)cn.methods) {
            System.out.println(method.name);
        }
//        cr.accept(cvis, ClassReader.SKIP_DEBUG);
        cn.accept(cw);
    }
}
