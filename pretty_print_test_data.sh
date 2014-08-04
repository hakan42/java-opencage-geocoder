#! /bin/sh -x

for d in de-by-germering tr-antalya-kas tr-antalya-serik tr-isparta-keciborlu
do

    python -m json.tool < $(dirname $0)/library/src/test/resources/${d}.json > $(dirname $0)/library/src/test/resources/${d}-pretty.json
done
