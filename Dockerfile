FROM ubuntu:latest
USER root

RUN apt-get update && apt-get upgrade -y --no-install-recommends \
    build-essential

RUN apt install openjdk-17-jdk -y

RUN apt install vim -y

ENV CA_WD=/home/developer/cost_accounting

RUN groupadd developers

RUN useradd -m -g developers developer

RUN mkdir $CA_WD && chown developer:developers $CA_WD && chmod 770 $CA_WD

USER developer

WORKDIR $CA_WD

COPY . $CA_WD

CMD [ "bash" ]