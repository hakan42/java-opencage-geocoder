#!/bin/sh

if [[ -z "$CONFIG" ]]
then
        CONFIG=${XDG_CONFIG_HOME:-$HOME/.config}/opencagedata
fi

API_URL=http://api.opencagedata.com/geocode/v1/json

source $CONFIG

if [ "$API_KEY" = "" ];then
echo -e "\e[0;33mWarning, your API key is not set.\nPlease create $CONFIG with a line starting with API_KEY= and add your OpenCageData key\e[00m"
fi

curl "http://api.opencagedata.com/geocode/v1/json?key=${API_KEY}&q=$@"
echo
