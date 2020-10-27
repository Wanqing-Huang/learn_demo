package com.example.asm_demo;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LifecycleClassVisitor extends ClassVisitor {
    private String className;
    private LogUtil logger = new LogUtil();

    public LifecycleClassVisitor(ClassVisitor cv) {
        /**
         * 参数1：ASM API版本，源码规定只能为4，5，6
         * 参数2：ClassVisitor不能为 null
         */
        super(Opcodes.ASM6, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className = name;
        logger.warn("[CustomPlugin]start to visit class: " + className);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

        if (name.startsWith("on")) {
            logger.warn("[CustomPlugin]find lifecycle method. methodName = " + name);

            //处理onXX()方法
            return new LifecycleMethodVisitor(mv, className, name);
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        logger.warn("[CustomPlugin]end visit class: " + className);
    }


}

