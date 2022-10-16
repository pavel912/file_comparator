.DEFAULT_GOAL := build-run
run-dist:
	./build/install/app/bin/app $(arg1) $(arg2)

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
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

update-deps:
	./gradlew useLatestVersions


build-run: build run

.PHONY: build