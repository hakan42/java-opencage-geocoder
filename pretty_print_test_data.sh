#! /bin/sh -x

for d in \
    de-by-germering tr-antalya-kas tr-antalya-serik tr-isparta-keciborlu \
    br--1.05--46.76 cn-+30.63-+122.07 de-+53.05-+8.73 it-+42.01-+12.50 jp-+35.69-+139.78
do

    jq "." < $(dirname $0)/library/src/test/resources/${d}.json > $(dirname $0)/library/src/test/resources/${d}-pretty.json
done
