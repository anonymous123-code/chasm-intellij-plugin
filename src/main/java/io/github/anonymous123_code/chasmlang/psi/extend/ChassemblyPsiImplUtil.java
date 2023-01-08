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
        ChassemblyLambdaExpression lambdaExpression = value.getLambdaExpression();
        return lambdaExpression == null ? value.getTernaryExpression().getType() : lambdaExpression.getType();
    }

    public static Type getType(ChassemblyTernaryExpression value) {
        if (value.getTernaryExpressionList().size() > 0) return new AnyType();
        return value.getBooleanOrExpression().getType();
    }

    public static Type getType(ChassemblyLambdaExpression value) {
        return new FunctionType(new AnyType(),value.getExpression().getType());
    }

    public static Type getType(ChassemblyBooleanOrExpression value) {
        List<ChassemblyBooleanAndExpression> booleanAndExpressionList = value.getBooleanAndExpressionList();
        return booleanAndExpressionList.size() == 1 ? booleanAndExpressionList.get(0).getType() : SimpleType.bool();
    }

    public static Type getType(ChassemblyBooleanAndExpression value) {
        List<ChassemblyBitwiseOrExpression> bitwiseOrExpressionList = value.getBitwiseOrExpressionList();
        return bitwiseOrExpressionList.size() == 1 ? bitwiseOrExpressionList.get(0).getType() : SimpleType.bool();
    }

    public static Type getType(ChassemblyBitwiseOrExpression value) {
        List<ChassemblyBitwiseXorExpression> bitwiseXorExpressionList = value.getBitwiseXorExpressionList();
        return bitwiseXorExpressionList.size() == 1 ? bitwiseXorExpressionList.get(0).getType() : SimpleType.integer();
    }

    public static Type getType(ChassemblyBitwiseXorExpression value) {
        List<ChassemblyBitwiseAndExpression> bitwiseAndExpressionList = value.getBitwiseAndExpressionList();
        return bitwiseAndExpressionList.size() == 1 ? bitwiseAndExpressionList.get(0).getType() : SimpleType.integer();
    }

    public static Type getType(ChassemblyBitwiseAndExpression value) {
        List<ChassemblyEqualityExpression> equalityExpressionList = value.getEqualityExpressionList();
        return equalityExpressionList.size() == 1 ? equalityExpressionList.get(0).getType() : SimpleType.integer();
    }

    public static Type getType(ChassemblyEqualityExpression value) {
        List<ChassemblyRelationalExpression> relationalExpressionList = value.getRelationalExpressionList();
        return relationalExpressionList.size() == 1 ? relationalExpressionList.get(0).getType() : SimpleType.bool();
    }

    public static Type getType(ChassemblyRelationalExpression value) {
        List<ChassemblyShiftExpression> shiftExpressionList = value.getShiftExpressionList();
        return shiftExpressionList.size() == 1 ? shiftExpressionList.get(0).getType() : SimpleType.bool();
    }

    public static Type getType(ChassemblyShiftExpression value) {
        List<ChassemblyAdditiveExpression> additiveExpressionList = value.getAdditiveExpressionList();
        return additiveExpressionList.size() == 1 ? additiveExpressionList.get(0).getType() : SimpleType.integer();
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
