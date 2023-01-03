package io.github.anonymous123_code.chasmlang;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class ChassemblyColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Keyword", ChassemblySyntaxHighlighter.KEYWORD),
            new AttributesDescriptor("String literal", ChassemblySyntaxHighlighter.STRING),
            new AttributesDescriptor("Number literal", ChassemblySyntaxHighlighter.NUMBER),
            new AttributesDescriptor("Function call (Buggy)", ChassemblySyntaxHighlighter.FUNCTION_CALL),
            new AttributesDescriptor("Operator", ChassemblySyntaxHighlighter.OPERATOR),
            new AttributesDescriptor("Block comment", ChassemblySyntaxHighlighter.BLOCK_COMMENT),
            new AttributesDescriptor("Line comment", ChassemblySyntaxHighlighter.LINE_COMMENT),
            new AttributesDescriptor("Parentheses", ChassemblySyntaxHighlighter.PARENTHESES),
            new AttributesDescriptor("Brackets", ChassemblySyntaxHighlighter.BRACKETS),
            new AttributesDescriptor("Braces", ChassemblySyntaxHighlighter.BRACES),
            new AttributesDescriptor("Dot", ChassemblySyntaxHighlighter.DOT),
            new AttributesDescriptor("Comma", ChassemblySyntaxHighlighter.COMMA),
            new AttributesDescriptor("Bad value", ChassemblySyntaxHighlighter.BAD_CHARACTER)
    };


    @Override
    public @Nullable Icon getIcon() {
        return AllIcons.FileTypes.Text;
    }

    @Override
    public @NotNull SyntaxHighlighter getHighlighter() {
        return new ChassemblySyntaxHighlighter();
    }

    @Override
    public @NonNls @NotNull String getDemoText() {
        return "{\n" +
                "    // This is a Single-Line Comment\n" +
                "    /* And this one\n" +
                "    spans over multiple lines */\n" +
                "    aIntegerProperty: 42,\n" +
                "    aMap: {\n" +
                "        aIntegerProperty: aIntegerProperty\n" +
                "    }\n" +
                "    aBooleanProperty: false,\n" +
                "    aLambda: args -> args[0],\n" +
                "    theResult: aLambda([aMap.aIntegerProperty]),\n" +
                "    transformers: [\n" +
                "        \n" +
                "    ] <- a mistake!!\n" +
                "}\n";
    }

    @Override
    public @Nullable Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @Override
    public @NotNull @NlsContexts.ConfigurableName String getDisplayName() {
        return "Chasm";
    }
}
