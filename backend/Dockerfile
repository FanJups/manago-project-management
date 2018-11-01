FROM node:carbon
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install
COPY . .
ENV NODE_ENV docker
CMD [ "npm", "start" ]
