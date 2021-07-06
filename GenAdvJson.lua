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

--GenAdvItem("backtones:castle_bg", "watch_your_step", "root", true);
