package com.cyq7on.crud.entity;

public class UploadImageResponse {
    /**
     * success : true
     * code : success
     * message : Upload success.
     * data : {"file_id":0,"width":1347,"height":465,"filename":"QQ图片20181202112600.png","storename":"KoPIXxWDqiZBpf5.png","size":44744,"path":"/2019/09/07/KoPIXxWDqiZBpf5.png","hash":"72CswcqvlRyF36tYnQDiBbIxaJ","url":"https://i.loli.net/2019/09/07/KoPIXxWDqiZBpf5.png","delete":"https://sm.ms/delete/72CswcqvlRyF36tYnQDiBbIxaJ","page":"https://sm.ms/image/KoPIXxWDqiZBpf5"}
     * RequestId : F66C2355-C0B2-4049-A7AD-7738E9C8C378
     */

    public boolean success;
    public String code;
    public String message;
    public DataBean data;
    public String RequestId;

    public static class DataBean {
        /**
         * file_id : 0
         * width : 1347
         * height : 465
         * filename : QQ图片20181202112600.png
         * storename : KoPIXxWDqiZBpf5.png
         * size : 44744
         * path : /2019/09/07/KoPIXxWDqiZBpf5.png
         * hash : 72CswcqvlRyF36tYnQDiBbIxaJ
         * url : https://i.loli.net/2019/09/07/KoPIXxWDqiZBpf5.png
         * delete : https://sm.ms/delete/72CswcqvlRyF36tYnQDiBbIxaJ
         * page : https://sm.ms/image/KoPIXxWDqiZBpf5
         */

        public int file_id;
        public int width;
        public int height;
        public String filename;
        public String storename;
        public int size;
        public String path;
        public String hash;
        public String url;
        public String delete;
        public String page;
    }
}
