package io.github.anonymous123_code.chasmlang.psi.extend;

import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import io.github.anonymous123_code.chasmlang.psi.*;
import io.github.anonymous123_code.chasmlang.type.*;

import java.util.List;

public class ChassemblyPsiImplUtil {
    public static Type getType(ChassemblyMapValue value) {
        return value.getExpression().getType();
    }

    public static Type getType(ChassemblyExpression value) {
        return value.getLambdaExpression() == null ? value.getTernaryExpression().getType() : value.getLambdaExpression().getType();
    }

    public static Type getType(ChassemblyTernaryExpression value) {
        if (value.getTernaryExpressionList().size() > 0) return new AnyType();
        return value.getBooleanOrExpression().getType();
    }

    public static Type getType(ChassemblyLambdaExpression value) {
        return new FunctionType(new AnyType(),value.getExpression().getType());
    }

    public static Type getType(ChassemblyBooleanOrExpression value) {
        return value.getBooleanAndExpressionList().size() == 1 ? value.getBooleanAndExpressionList().get(0).getType() : SimpleType.bool();
    }

    public static Type getType(ChassemblyBooleanAndExpression value) {
        return value.getBitwiseOrExpressionList().size() == 1 ? value.getBitwiseOrExpressionList().get(0).getType() : SimpleType.bool();
    }

    public static Type getType(ChassemblyBitwiseOrExpression value) {
        return value.getBitwiseXorExpressionList().size() == 1 ? value.getBitwiseXorExpressionList().get(0).getType() : SimpleType.integer();
    }

    public static Type getType(ChassemblyBitwiseXorExpression value) {
        return value.getBitwiseAndExpressionList().size() == 1 ? value.getBitwiseAndExpressionList().get(0).getType() : SimpleType.integer();
    }

    public static Type getType(ChassemblyBitwiseAndExpression value) {
        return value.getEqualityExpressionList().size() == 1 ? value.getEqualityExpressionList().get(0).getType() : SimpleType.integer();
    }

    public static Type getType(ChassemblyEqualityExpression value) {
        return value.getRelationalExpressionList().size() == 1 ? value.getRelationalExpressionList().get(0).getType() : SimpleType.bool();
    }

    public static Type getType(ChassemblyRelationalExpression value) {
        return value.getShiftExpressionList().size() == 1 ? value.getShiftExpressionList().get(0).getType() : SimpleType.bool();
    }

    public static Type getType(ChassemblyShiftExpression value) {
        return value.getAdditiveExpressionList().size() == 1 ? value.getAdditiveExpressionList().get(0).getType() : SimpleType.integer();
    }

    public static Type getType(ChassemblyAdditiveExpression value) {
        List<ChassemblyMultiplicativeExpression> multiplicativeExpressionList = value.getMultiplicativeExpressionList();
        if (multiplicativeExpressionList.size() == 1) return multiplicativeExpressionList.get(0).getType();
        Type leftType = multiplicativeExpressionList.get(0).getType();
        if (leftType instanceof MapType) {

        }
        if (leftType instanceof ListType) {

        }
        List<LeafPsiElement> leaves = PsiTreeUtil.getChildrenOfTypeAsList(value, LeafPsiElement.class);

        return new AnyType();
    }

    public static Type getType(ChassemblyMultiplicativeExpression value) {
        List<ChassemblyUnaryExpression> unaryExpressionList = value.getUnaryExpressionList();
        Type leftType = unaryExpressionList.get(0).getType();
        if (unaryExpressionList.size() == 1) return leftType;
        if (SimpleType.float_().equals(leftType)) return SimpleType.float_();
        Type floatType = SimpleType.float_();
        for (int i = 1; i < unaryExpressionList.size(); i++) {
            if (floatType.equals(unaryExpressionList.get(i).getType())) return floatType;
        }
        return SimpleType.integer();
    }

    public static Type getType(ChassemblyUnaryExpression value) {
        return new AnyType();
    }


}
