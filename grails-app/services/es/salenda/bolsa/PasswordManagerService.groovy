package es.salenda.bolsa

import cr.co.arquetipos.password.*

class PasswordManagerService {

    // Used for caching passwords for items
    private static Hashtable cache = new Hashtable()

    // How many seconds is the cache considered live
    public static int cacheDuration = 600

    boolean transactional = true

    /**
     * Encrypts and stores a data item using EncryptedData records.  It will
     * not cache the password.
     * @param key Item ID to store
     * @param data Plaintext data to encrypt
     * @param encryptionPassword Encryption password to use
     * @see EncryptedData
     */
    EncryptedData store(String key, String data, String encryptionPassword) {
        def item = EncryptedData.getOrCreate(key)
        item.encrypt(data, encryptionPassword)
        item.save()
        return item
    }

    /**
     * Attempts to retrieve a data item by its key, and decrypt it with the
     * provided decryption password.  If the decryption is successful, it will
     * cache the password for the specified period of time.
     *
     * @param   key     Item ID to store
     * @param   encryptionPassword  Encryption password originally used.
     * Optional parameter, if not provided it will attempt to retrieve the
     * value from the password cache.
     * @return      Decrypted string
     */
    def retrieve(String key, String encryptionPassword = '') {
        KeyCache cachedItem = null
        if (!encryptionPassword) {
            cachedItem = (KeyCache) cache[key]
            assert cachedItem, "A password is required"
            if (cachedItem.isExpired(cacheDuration)) {
                cache.remove(key)
                assert !cachedItem.isExpired(cacheDuration), "A password is required"
            }
            cachedItem.touch()
            encryptionPassword = cachedItem.password
        }
        EncryptedData item = EncryptedData.get(key)
		if(item){
        	item.decrypt(encryptionPassword)
			if (!cachedItem && item.decryptedData){
            	cachedItem = new KeyCache(key:key, password:encryptionPassword)
            	cache[key] = cachedItem
        	}
			return item.decryptedData
		}else{
			return null
		}
    }

    /**
     * Forgets all cached passwords
     */
    public static void flushCache()
    {
        cache = new Hashtable()
    }

}
