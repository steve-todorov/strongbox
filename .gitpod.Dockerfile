FROM gitpod/workspace-full

RUN set -ex \
 && mkdir -p ~/.m2/ && curl -o ~/.m2/settings.xml https://strongbox.github.io/assets/resources/maven/settings.xml \
 && bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh \
    && sdk install java 8.0.265-open \
    && sdk default java 8.0.265-open"
 
