local outFile = nil;
local modName = "backtones";

local function GenAdvItem(iconName, advName, parentPath, isChall)
	print(string.format("Creating: %s:%s, icon = %s", modName, advName, iconName))
	local path = string.format("src\\main\\resources\\data\\%s\\advancements\\%s.json", modName, advName);
	outFile = io.open(path,"w");

	local border = '"frame": "task"';
    if (isChall) then
		border = '"frame": "challenge"';
	end

	local content = string.format('{\n\t\t"display": {\n\t\t\t\t"icon": {\n\t\t\t\t\t\t"item": "'..iconName..'"\n\t\t\t\t},\n\t\t\t\t"title": {\n\t\t\t\t\t\t"translate": "'..modName..'.advancements.'..advName..'.title"\n\t\t\t\t},\n\t\t\t\t"description": {\n\t\t\t\t\t\t"translate": "'..modName..'.advancements.'..advName..'.description"\n\t\t\t\t},\n\t\t\t\t"show_toast": true,\n\t\t\t\t"announce_to_chat": true,\n\t\t\t\t'..border..'\n\t\t},\n\t\t"parent": "'..modName..':'..parentPath..'",\n\t\t"criteria": {\n\t\t\t\t"impossible": {\n\t\t\t\t\t\t"trigger": "minecraft:impossible"\n\t\t\t\t}\n\t\t}\n}')

	outFile:write(content);

	outFile:close();
end

GenAdvItem("backtones:mongo_sword", "mongo_sword", "root", false);
GenAdvItem("backtones:breakable_wall", "breakable_wall", "mongo_sword", true);
GenAdvItem("backtones:red_bow", "red_bow", "root", false);
GenAdvItem("backtones:ceramic_bow", "ceramic_bow", "red_bow", true);

--GenItem("misc", "hell_coin_base");


--GenBlock("fade_planks");
--GenBlock("flesh_block_1");
--GenBlock("flesh_block_brain");
--GenBlock("flesh_block_eye");
--GenBlock("flesh_block_mouth");

-- GenItem("misc", "nano_mender_greater");
-- GenItem("misc", "package_fade_armor_diamond");
-- for i = 1,4 do 
-- 	GenItem("misc", "armor_qsh_"..i);
-- end

-- for i = 1,4 do 
-- 	GenItem("misc", "mor_armor_"..i);
-- end

--for i = 0,15 do
--	GenBlock("skyland_runestone_"..i);
--end

--GenBlock("skyland_pebble");
--GenBlock("block_loop_16");
--GenBlock("random_tp");

--GenItem("food", "float_food")
--GenItem("misc", "cycle_stone_shard")
--GenItem("misc", "disturb_measure")
--GenItem("skill", "skill_radar_creature")
--GenItem("food", "chilli")
--GenItem("food", "digest_pills")

-- for i = 1,4 do 
-- 	GenItem("armor", "l_m_armor_" .. i)
-- end

--//{
--	"type": "item",
--	"name": "minecraft:golden_apple",
--	"weight": 20
--},
