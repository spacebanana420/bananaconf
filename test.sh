echo "Building the test"
scala-cli --power package test src -o build/test.jar
echo "Running the test"
build/test.jar && rm build/test.jar
