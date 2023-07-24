# Start with a base image containing Java runtime
FROM gradle:latest

# Set the working directory in the image
WORKDIR /app

# Copy the Gradle files into the Docker image
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Grant execution permissions for the gradlew script
RUN chmod +x ./gradlew

# Copy the rest of your source code
COPY src src

# Install chrome browser
RUN apt-get update && apt-get install -y \
    apt-utils \
    curl \
    unzip \
    xvfb \
    libxi6 \
    libgconf-2-4 \
    chromium-browser \
    && rm -rf /var/lib/apt/lists/* /var/cache/apt/*

# List files in the current directory
RUN ls -la
# Set chrome browser as default
RUN ln -s /usr/bin/chromium-browser /usr/bin/google-chrome

# Chrome browser to run in headless mode
ENV DISPLAY=:99

# Set the entry point for the container to execute your specific test task
CMD ["./gradlew", "taskProvider_registrationTest"]
