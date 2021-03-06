# http4s Executable via GraalVM Native Image

This repository demonstrates how to compile an http4s application to a native executable using GraalVM's Native Image. The accompanying [blog post](https://www.inner-product.com/posts/serverless-scala-services-with-graalvm/) has all the details. 

To quickly get going:

1. Having installed GraalVM Native Image run the `nativeImageLocal` task to generate an executable.

2. Optionally, run `startup-benchmark.sh` and `sustained-benchmark.sh` to
   benchmark against the JVM, and `analysis.py` to generate graphs.


To build a Linux executable:

1. Build the Docker image in the `docker` directory

``` sh
cd docker
docker build -t inner-product/graalvm-native-image .
```

2. Run the `nativeImage` task from sbt. The result will be a Linux executable. 


## License

Distributed under the MIT license.

Copyright 2020 Inner Product LLC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
