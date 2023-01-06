package io.github.anonymous123_code.chasmlang;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import static com.intellij.patterns.PlatformPatterns.*;

import com.intellij.util.ProcessingContext;
import io.github.anonymous123_code.chasmlang.psi.ChassemblyTypes;
import org.jetbrains.annotations.NotNull;

public class ChasmCompletionContributor extends CompletionContributor {
    public ChasmCompletionContributor() {
        extend(
                CompletionType.BASIC,
                and(
                        psiElement(ChassemblyTypes.IDENTIFIER).withParent(psiElement(ChassemblyTypes.MAP_KEY)),
                        not(psiElement(ChassemblyTypes.IDENTIFIER).withParent(psiElement(ChassemblyTypes.MAP_KEY).withParent(psiElement(/*MAP_KEY_VALUE_PAIR*/).withParent(psiElement(/*MAP_EXPRESSION*/).inside(true, psiElement(ChassemblyTypes.MAP_EXPRESSION))))))
                ),
                new CompletionProvider<>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
                        result.addElement(LookupElementBuilder.create("transformations"));
                    }
                }

        );
        extend(
                CompletionType.BASIC,
                psiElement(ChassemblyTypes.IDENTIFIER).withParent(psiElement(ChassemblyTypes.MAP_KEY).withParent(psiElement(/*MAP_KEY_VALUE_PAIR*/).withParent(psiElement(/*MAP_EXPRESSION*/).inside(true, psiElement(ChassemblyTypes.MAP_EXPRESSION))))),
                new CompletionProvider<>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
                        result.addElement(LookupElementBuilder.create("target"));
                        result.addElement(LookupElementBuilder.create("node"));
                        result.addElement(LookupElementBuilder.create("start"));
                        result.addElement(LookupElementBuilder.create("end"));
                        result.addElement(LookupElementBuilder.create("apply"));
                        result.addElement(LookupElementBuilder.create("read"));
                    }
                }
        );
    }
}
