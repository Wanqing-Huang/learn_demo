package com.example.asm_demo;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


/**
 * @author 周旭
 * @company 伊柯夫
 * @e-mail 374952705@qq.com
 * @time 2019/12/16
 * @descripe
 */


public class LifecycleMethodVisitor extends MethodVisitor {
    private String className;
    private String methodName;

    public LifecycleMethodVisitor(MethodVisitor methodVisitor, String className, String methodName) {
        super(Opcodes.ASM6, methodVisitor);
        this.className = className;
        this.methodName = methodName;
    }


    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
//        System.out.println("MethodVisitor visitAnnotation desc------"+desc);
//        System.out.println("MethodVisitor visitAnnotation visible------"+visible);
//        AnnotationVisitor annotationVisitor = mv.visitAnnotation(desc, visible);
//        if (desc.contains("CheckLogin")){
//            return new TestAnnotationVistor(annotationVisitor);
//        }
//        return annotationVisitor;
        return super.visitAnnotation(desc, visible);
    }



    //方法执行前插入
    @Override
    public void visitCode() {
        super.visitCode();
        LogUtil.info("MethodVisitor insert code.");

        mv.visitLdcInsn("TAG");
        mv.visitLdcInsn(className + "------->" + methodName);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "e", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(Opcodes.POP);
    }

    //方法执行后插入
    @Override
    public void visitInsn(int opcode) {
//        if (opcode==Opcodes.RETURN){
//            mv.visitLdcInsn("TAG");
//            mv.visitLdcInsn(className + "------->" + methodName);
//            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "e", "(Ljava/lang/String;Ljava/lang/String;)I", false);
//            mv.visitInsn(Opcodes.POP);
//        }
        super.visitInsn(opcode);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }


}
