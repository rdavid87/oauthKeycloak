print('Start creating database ##########################')
db = db.getSiblingDB('moviesdb');
db.createUser(
    {
        user: 'root',
        pwd:  'root',
        roles: [{role: 'readWrite', db: 'moviesdb'}],
    }
);

db = db.getSiblingDB('usersdb');
db.createUser(
    {
        user: 'root',
        pwd:  'root',
        roles: [{role: 'readWrite', db: 'usersdb'}],
    }
);
print('End creating database ##########################')