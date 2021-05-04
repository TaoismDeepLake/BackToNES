local outFile = nil;
local modName = "idealland";
local blockName = "grid_normal";

local function GenModelBlockItem()
	local path = string.format("1.txt");
	outFile = io.open(path,"w");

--	for i = 0, 100 do
--		outFile:write(string.format('new BlockBase("ch%d", Material.CLAY).setCreativeTab(ModCreativeTab.MAIN_TAB),\n',i));
--	end

	local list = {
		"harp",
		"basedrum",
		"snare",
		"hat",
		"bass",
		"flute",
		"bell",
		"guitar",
		"chime",
		"xylophone"
	}

	for i,key in ipairs(list) do
		--outFile:write(string.format('tile.dungeon_brick_%d.name=Dungeon Brick Block\n',i));
		--outFile:write(string.format('"%s":{"category": "blocks","subtitle": "%s","sounds": [{"name":  "noteblockne:%s", "stream":  true}]},\n',key,key,key));
		outFile:write(string.format('public static SoundEvent %s = new ModSoundEvent("%s__p");\n',key,key));
	end
--	local tp = {'a','b','c','d','e','f','g','h','i','j','k'}
--
-- 	for i = 1, 12 do
--		local cur_tp = tp[i];
--		outFile:write("public static final ItemBase[] M_"..cur_tp.." =\n");
--		outFile:write("		new ItemBase[]{\n");
--		outFile:write('				new ItemBase("m_'..cur_tp..'_1"),\n');
--		outFile:write('				new ItemBase("m_'..cur_tp..'_2"),\n');
--		outFile:write('				new ItemBase("m_'..cur_tp..'_3"),\n');
--		outFile:write('				new ItemBase("m_'..cur_tp..'_4"),\n');
--		outFile:write('		};\n\n');
--
--	end

	outFile:close();
end

GenModelBlockItem();

print("Done");
