.DEFAULT_GOAL := build-run
run-dist:
	./app/build/install/app/bin/app $(arg1) $(arg2)
	
run-dist-json-test:
	./app/build/install/app/bin/app ./app/src/test/resources/jsonFiles/file1.json ./app/src/test/resources/jsonFiles/file2.json

run-dist-yaml-test:
	./app/build/install/app/bin/app ./app/src/test/resources/yamlFiles/file1.yaml ./app/src/test/resources/yamlFiles/file2.yaml

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