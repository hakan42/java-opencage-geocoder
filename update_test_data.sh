#! /bin/sh -x

$(dirname $0)/geocode.sh germering \
	     > $(dirname $0)/library/src/test/resources/de-by-germering.json

$(dirname $0)/geocode.sh kas,antalya \
	     > $(dirname $0)/library/src/test/resources/tr-antalya-kas.json

$(dirname $0)/geocode.sh serik \
	     > $(dirname $0)/library/src/test/resources/tr-antalya-serik.json

$(dirname $0)/geocode.sh keciborlu \
	     > $(dirname $0)/library/src/test/resources/tr-isparta-keciborlu.json

$(dirname $0)/pretty_print_test_data.sh

