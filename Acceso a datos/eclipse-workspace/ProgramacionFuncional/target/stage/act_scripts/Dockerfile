FROM actframework/java-8
ENV APPHOME=/var/act-app/
RUN mkdir -p $APPHOME
WORKDIR $APPHOME
ADD *.tar.gz $APPHOME

EXPOSE ${docker.http.port}/tcp
EXPOSE ${docker.cli.port}/tcp

CMD $APPHOME/run
