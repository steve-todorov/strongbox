FROM gitpod/workspace-full

ENV JDK_VERSION=8.0.265-open

RUN set -ex \
 && mkdir -p ~/.m2/ && curl -o ~/.m2/settings.xml https://strongbox.github.io/assets/resources/maven/settings.xml \
 && bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && sdk install java $JDK_VERSION && sdk default java sdk i java $JDK_VERSION"
 