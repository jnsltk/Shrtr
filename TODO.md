# Shrtr API
A URL shortener service API

### Todo

- [ ] Expiring database records
- [ ] Dockerize application
- [ ] Set up CI/CD, deploy on VPS 
- [ ] Testing

### In Progress

- [ ] Expiring database records

### Done ✓

- [x] Base functionality of the API
  - [x] POST request providing alias and link
  - [x] POST request providing only link (using murmur3 hash)
  - [x] Storing URLs in a postgres database
- [x] URL validation
- [x] Persist data
