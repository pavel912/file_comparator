.DEFAULT_GOAL := build-run
run-dist:
	./app/build/install/app/bin/app $(arg1) $(arg2)
	
run-dist-json-flat-test:
	./app/build/install/app/bin/app ./app/src/test/resources/jsonFiles/file1.json ./app/src/test/resources/jsonFiles/file2.json

run-dist-yaml-flat-test:
	./app/build/install/app/bin/app ./app/src/test/resources/yamlFiles/file1.yaml ./app/src/test/resources/yamlFiles/file2.yaml

run-dist-json-nested-test:
	./app/build/install/app/bin/app ./app/src/test/resources/jsonFiles/fileNested1.json ./app/src/test/resources/jsonFiles/fileNested2.json

run-dist-yaml-nested-test:
	./app/build/install/app/bin/app ./app/src/test/resources/yamlFiles/fileNested1.yaml ./app/src/test/resources/yamlFiles/fileNested2.yaml

run-dist-plain-test:
	./app/build/install/app/bin/app ./app/src/test/resources/jsonFiles/fileNested1.json ./app/src/test/resources/jsonFiles/fileNested2.json -f plain

clean:
	./app/gradlew -p app clean

build:
	 ./app/gradlew -p app clean build test

install:
	./app/gradlew -p app clean installDist

run:
	./app/gradlew -p app run

test:
	./app/gradlew -p app test

report:
	./app/gradlew -p app jacocoTestReport

lint:
	./app/gradlew -p app checkstyleMain checkstyleTest

update-deps:
	./app/gradlew -p app useLatestVersions


build-run: build run

.PHONY: build