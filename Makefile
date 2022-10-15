.DEFAULT_GOAL := build-run
run-dist:
	./app/build/install/app/bin/app $(arg1) $(arg2)

clean:
	./app/gradlew -p app clean

build:
	 ./app/gradlew -p app clean build

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