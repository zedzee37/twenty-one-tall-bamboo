package io.github.zedzee37.totbamboo.datagen;

import io.github.zedzee37.totbamboo.TTB;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class TTBLangProvider extends LanguageProvider {
    public TTBLangProvider(PackOutput output, String locale) {
        super(output, TTB.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(TTB.TWENTY_ONE_TALL_BAMBOO_BLOCK_ITEM.get(), "Twenty-One Tall Bamboo");
    }
}
