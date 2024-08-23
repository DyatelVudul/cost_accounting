FROM ubuntu:latest
USER root

# Update and upgrade apt packages
RUN apt-get update && apt-get upgrade -y --no-install-recommends

# Install required packages
RUN apt-get install -y \
    build-essential \
    openjdk-17-jdk \
    vim

# Set environment variable for the working directory
ENV CA_WD=/home/developer/cost_accounting

# Create a developers group and a developer user
RUN groupadd developers && \
    useradd -m -g developers developer

# Create and set permissions for the working directory
RUN mkdir -p $CA_WD && \
    chown developer:developers $CA_WD && \
    chmod 770 $CA_WD

# Switch to developer user
USER developer

# Set working directory
WORKDIR $CA_WD

# Copy files into the working directory
COPY . $CA_WD

# Default command
CMD ["bash"]
