FROM node:20.10.0-alpine
WORKDIR /voda
COPY ./frontend/voda/package.json .
RUN npm install
COPY ./frontend/voda .
RUN npm run build
RUN ls /voda/
COPY /voda/build .
