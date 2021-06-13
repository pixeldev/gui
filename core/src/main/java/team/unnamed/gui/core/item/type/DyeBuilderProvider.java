package team.unnamed.gui.core.item.type;

import org.bukkit.DyeColor;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

import static team.unnamed.gui.core.version.ServerVersionProvider.SERVER_VERSION_INT;

public class DyeBuilderProvider {

	private static final Map<DyeColor, String> COLORS_BY_DYE;

	static {
		COLORS_BY_DYE = new HashMap<>();

		for (DyeColor dyeColor : DyeColor.values()) {
			switch (dyeColor) {
				case GRAY: {
					COLORS_BY_DYE.put(dyeColor, "LIGHT_GRAY");
					break;
				}
				case SILVER: {
					COLORS_BY_DYE.put(dyeColor, "GRAY");
					break;
				}
				default: {
					COLORS_BY_DYE.put(dyeColor, dyeColor.name());
					break;
				}
			}
		}
	}

	protected static ItemBuilder createBuilder(String materialKey, DyeColor dyeColor, int amount) {
		Material material;
		short data;

		if (SERVER_VERSION_INT < 13) {
			material = Material.valueOf(materialKey);
			data = dyeColor.getWoolData();
		} else {
			if (materialKey.equals("STAINED_CLAY")) {
				materialKey = "TERRACOTTA";
			}

			material = Material.valueOf(
					COLORS_BY_DYE.get(dyeColor) + "_" + materialKey
			);
			data = 0;
		}

		return new SimpleItemBuilder(material, amount, data);
	}

}