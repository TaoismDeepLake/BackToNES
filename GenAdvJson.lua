local outFile = nil;
local modName = "backtones";

local function GenAdvItem(iconName, advName, parentPath, isChall, isHidden, isSilent)
	print(string.format("Creating: %s:%s, icon = %s", modName, advName, iconName))
	local path = string.format("src\\main\\resources\\data\\%s\\advancements\\%s.json", modName, advName);
	outFile = io.open(path,"w");

	local border = '"frame": "task"';
    if (isChall) then
		border = '"frame": "challenge"';
	end

	local hidden = "false";
    if (isHidden) then
		hidden = "true";
	end

	local displaying = "true";
	if (isSilent) then
		displaying = "false"
	end

	local content = string.format('{\n\t\t"display": {\n\t\t\t\t"icon": {\n\t\t\t\t\t\t"item": "'..iconName..'"\n\t\t\t\t},\n\t\t\t\t"title": {\n\t\t\t\t\t\t"translate": "'..modName..'.advancements.'..advName..'.title"\n\t\t\t\t},\n\t\t\t\t"description": {\n\t\t\t\t\t\t"translate": "'..modName..'.advancements.'..advName..'.description"\n\t\t\t\t},\n\t\t\t\t"hidden": '..hidden..',\n\t\t\t\t"show_toast": '..displaying..',\n\t\t\t\t"announce_to_chat": '..displaying..',\n\t\t\t\t'..border..'\n\t\t},\n\t\t"parent": "'..modName..':'..parentPath..'",\n\t\t"criteria": {\n\t\t\t\t"impossible": {\n\t\t\t\t\t\t"trigger": "minecraft:impossible"\n\t\t\t\t}\n\t\t}\n}')

	outFile:write(content);

	outFile:close();
end

GenAdvItem("backtones:great_key_1", "dungeon_1", "root", true, true, false);
GenAdvItem("backtones:great_key_1", "dungeon_2", "root", true, true, false);
GenAdvItem("backtones:great_key_1", "dungeon_3", "root", true, true, false);
GenAdvItem("backtones:great_key_1", "dungeon_4", "root", true, true, false);
GenAdvItem("backtones:great_key_1", "dungeon_5", "root", true, true, false);
--GenAdvItem("backtones:devil_wing", "devil_wing", "breakable_wall", false, false, false);
--GenAdvItem("backtones:lamp", "lamp", "root", false, false, false);
--GenAdvItem("backtones:salt", "salt", "root", false, false, false);
--GenAdvItem("backtones:boots", "boots", "root", false, false, false);
--GenAdvItem("backtones:sabre", "sabre", "root", false, false, false);
--GenAdvItem("backtones:jar", "jar", "root", false, false, false);
--GenAdvItem("backtones:magical_rod", "magical_rod", "root", false, false, false);
--GenAdvItem("backtones:coin", "treasure_a_1", "root", false, false, false);

--GenAdvItem("backtones:coin", "treasure_a_1", "root", false, true, true);
--for i = 2, 10 do
--	GenAdvItem("backtones:coin", "treasure_a_"..i, "treasure_a_"..(i-1), false, true, true);
--end
--
--GenAdvItem("backtones:coin", "treasure_b_1", "root", false, true, true);
--for i = 2, 10 do
--	GenAdvItem("backtones:coin", "treasure_b_"..i, "treasure_b_"..(i-1), false, true, true);
--end
--
--GenAdvItem("backtones:quiver", "treasure_c_1", "root", false, true, true);
--for i = 2, 10 do
--	GenAdvItem("backtones:quiver", "treasure_c_"..i, "treasure_c_"..(i-1), false, true, true);
--end