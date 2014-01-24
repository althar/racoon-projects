(function addXhrProgressEvent($) {
    var originalXhr = $.ajaxSettings.xhr;
    $.ajaxSetup({
        downloadProgress: function() { console.log("standard download progress callback"); },
        uploadProgress: function() { console.log("standard upload progress callback"); },
        xhr: function() {
            var req = originalXhr(), that = this;
            if (req) {
                if (typeof req.addEventListener == "function") {
                    req.addEventListener("progress", function(evt) {
                        that.downloadProgress(evt);
                    },false);
                }
                if(typeof req.upload.addEventListener == "function") {
                    req.upload.addEventListener("progress", function(evt) {
                        that.uploadProgress(evt);
                    },false);
                }
            }
            return req;
        }
    });
})(jQuery);