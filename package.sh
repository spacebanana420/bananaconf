echo "Building the jar library"
scala-cli --power package --library test src -o build/bananaconf.jar
echo "Packaging the source code"
7z a build/bananaconf.zip src -mx8 -mmt8
