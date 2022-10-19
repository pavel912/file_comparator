.DEFAULT_GOAL := build-run
run-dist:
	./build/install/app/bin/app $(arg1) $(arg2)

run-dist-json-flat-test:
	./build/install/app/bin/app ./src/test/resources/jsonFiles/file1.json ./src/test/resources/jsonFiles/file2.json

run-dist-yaml-flat-test:
	./build/install/app/bin/app ./src/test/resources/yamlFiles/file1.yaml ./src/test/resources/yamlFiles/file2.yaml

run-dist-json-nested-test:
	./build/install/app/bin/app ./src/test/resources/jsonFiles/fileNested1.json ./src/test/resources/jsonFiles/fileNested2.json

run-dist-yaml-nested-test:
	./build/install/app/bin/app ./src/test/resources/yamlFiles/fileNested1.yaml ./src/test/resources/yamlFiles/fileNested2.yaml

run-dist-plain-test:
	./build/install/app/bin/app ./src/test/resources/jsonFiles/fileNested1.json ./src/test/resources/jsonFiles/fileNested2.json -f plain

run-dist-json-format-test:
	./build/install/app/bin/app -f json ./src/test/resources/jsonFiles/file1.json ./src/test/resources/jsonFiles/file2.json

clean:
	./gradlew clean

build:
	 ./gradlew clean build test

install:
	./gradlew clean installDist

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew test jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

update-deps:
	./gradlew useLatestVersions


build-run: build run

.PHONY: build