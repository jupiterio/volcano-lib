package io.github.jupiterio.volcanolib.text;

import net.minecraft.text.Text;
import net.minecraft.text.MutableText;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.text.TextColor;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.util.Formatting;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;

public class TextBuilder {
    private MutableText text;
    private MutableText root;

    private TextBuilder(MutableText text) {
        this.root = new LiteralText("");
        this.text = text;
    }

    public static TextBuilder builder() {
        return new TextBuilder(new LiteralText(""));
    }

    public TextBuilder text(Text text) {
        this.root.append(this.text);
        this.text = (MutableText) text;
        return this;
    }

    public TextBuilder text(String msg) {
        return this.text(new LiteralText(msg));
    }

    public TextBuilder translate(String msg, Object... with) {
        return this.text(new TranslatableText(msg, with));
    }

    public TextBuilder formatted(Formatting... formattings) {
        this.text.formatted(formattings);
        return this;
    }

    public TextBuilder formatted(Formatting formatting) {
        this.text.formatted(formatting);
        return this;
    }

    public TextBuilder black() {
        this.formatted(Formatting.BLACK);
        return this;
    }

    public TextBuilder darkBlue() {
        this.formatted(Formatting.DARK_BLUE);
        return this;
    }

    public TextBuilder darkGreen() {
        this.formatted(Formatting.DARK_GREEN);
        return this;
    }

    public TextBuilder darkAqua() {
        this.formatted(Formatting.DARK_AQUA);
        return this;
    }

    public TextBuilder darkRed() {
        this.formatted(Formatting.DARK_RED);
        return this;
    }

    public TextBuilder darkPurple() {
        this.formatted(Formatting.DARK_PURPLE);
        return this;
    }

    public TextBuilder gold() {
        this.formatted(Formatting.GOLD);
        return this;
    }

    public TextBuilder gray() {
        this.formatted(Formatting.GRAY);
        return this;
    }

    public TextBuilder darkGray() {
        this.formatted(Formatting.DARK_GRAY);
        return this;
    }

    public TextBuilder blue() {
        this.formatted(Formatting.BLUE);
        return this;
    }

    public TextBuilder green() {
        this.formatted(Formatting.GREEN);
        return this;
    }

    public TextBuilder aqua() {
        this.formatted(Formatting.AQUA);
        return this;
    }

    public TextBuilder red() {
        this.formatted(Formatting.RED);
        return this;
    }

    public TextBuilder lightPurple() {
        this.formatted(Formatting.LIGHT_PURPLE);
        return this;
    }

    public TextBuilder yellow() {
        this.formatted(Formatting.YELLOW);
        return this;
    }

    public TextBuilder white() {
        this.formatted(Formatting.WHITE);
        return this;
    }

    public TextBuilder obfuscated() {
        this.formatted(Formatting.OBFUSCATED);
        return this;
    }

    public TextBuilder bold() {
        this.formatted(Formatting.BOLD);
        return this;
    }

    public TextBuilder strikethrough() {
        this.formatted(Formatting.STRIKETHROUGH);
        return this;
    }

    public TextBuilder underline() {
        this.formatted(Formatting.UNDERLINE);
        return this;
    }

    public TextBuilder italic() {
        this.formatted(Formatting.ITALIC);
        return this;
    }

    public TextBuilder reset() {
        this.formatted(Formatting.RESET);
        return this;
    }

    public TextBuilder color(String name) {
        this.text.setStyle(this.text.getStyle().withColor(TextColor.parse(name)));
        return this;
    }

    public TextBuilder url(String url) {
        this.text.setStyle(this.text.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url)));
        return this;
    }

    public TextBuilder command(String cmd) {
        this.text.setStyle(this.text.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, cmd)));
        return this;
    }

    public TextBuilder suggest(String cmd) {
        this.text.setStyle(this.text.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, cmd)));
        return this;
    }

    public TextBuilder page(int page) {
        this.text.setStyle(this.text.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, String.valueOf(page))));
        return this;
    }

    public TextBuilder clipboard(String text) {
        this.text.setStyle(this.text.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, text)));
        return this;
    }

    public TextBuilder item(ItemStack stack) {
        return this.text(stack.toHoverableText());
    }

    public TextBuilder entity(Entity entity) {
        return this.text(entity.getDisplayName());
    }

    public TextBuilder hoverText(Text text) {
        this.text.setStyle(this.text.getStyle().withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, text)));
        return this;
    }

    public TextBuilder hoverItem(ItemStack stack) {
        this.text.setStyle(this.text.getStyle().withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new HoverEvent.ItemStackContent(stack))));
        return this;
    }

    public TextBuilder hoverEntity(Entity entity) {
        this.text.setStyle(this.text.getStyle().withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ENTITY, new HoverEvent.EntityContent(entity.getType(), entity.getUuid(), entity.getName()))));
        return this;
    }

    public TextBuilder insert(String text) {
        this.text.setStyle(this.text.getStyle().withInsertion(text));
        return this;
    }

    public Text build() {
        this.root.append(this.text);
        return this.root;
    }

}
