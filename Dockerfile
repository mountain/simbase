FROM openjdk:15-jdk-slim-buster

ENV SIMBASE_HOME /usr/local/simbase
ENV PATH $SIMBASE_HOME/bin:$PATH
RUN mkdir -p "$SIMBASE_HOME"
RUN mkdir -p "$SIMBASE_HOME/bin"
RUN mkdir -p "$SIMBASE_HOME/target"
WORKDIR $SIMBASE_HOME

COPY ./target/simbase-standalone.jar $SIMBASE_HOME/target
COPY ./bin/start $SIMBASE_HOME/bin/start

EXPOSE 7654
CMD ["/usr/local/simbase/bin/start"]
