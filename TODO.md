# Shrtr API
A URL shortener service API

### Todo

- [ ] URL validation
- [ ] Revise API format
- [ ] Logging
- [ ] Revise hashing
- [ ] Dockerize application
- [ ] Set up CI/CD, deploy on VPS 

### In Progress

- [ ] 

### Done ✓

- [x] Base functionality of the API
  - [x] POST request providing alias and link
  - [x] POST request providing only link (using murmur3 hash)
  - [x] Storing URLs in a postgres database