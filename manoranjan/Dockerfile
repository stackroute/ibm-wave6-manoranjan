FROM node:12
WORKDIR /app
COPY . .
RUN npm install
RUN npm install http-server -g
RUN npm run build
RUN echo $NG_CLI_ANYALYTICS
WORKDIR /app/dist/manoranjan
ENTRYPOINT ["http-server", "-c1", "-p", "4200"]
