local outFile = nil;
local modName = "idealland";
local blockName = "grid_normal";

local function GenModelBlockItem()
	local path = string.format("1.txt");
	outFile = io.open(path,"w");

    for i = 1, 40 do
        outFile:write(string.format('public static final RegistryObject<Block> BOX_%d = registerWithItem("box_%d", () ->new BlockAchvBox(ItemRegistry.MONGO_SWORD, AdvancementUtil.ACHV_MANTLE));\n', i, i))
    end
    outFile:close();
end

GenModelBlockItem();

print("Done");
