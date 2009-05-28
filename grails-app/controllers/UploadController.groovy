class UploadController {

    def index = {redirect(action: create)  }
    def create = {
        def map = ["totalRecords":"0","totalRecordsProcessed":"0","totalRecordsFailed":"0"]
        return[map: map]
    }
    def save = {

        def map = [:]
        def csvdata = []
        def downloadedfile = request.getFile('file');
        downloadedfile.transferTo(new File('grailstest.csv'))
        new File('grailstest.csv').eachLine { line ->
            csvdata << line.split('\t')
        }
        def headers = csvdata[0]
        def datarows = csvdata[1..-1]
        def totalHeaders = headers.iterator().size()

        map.put("totalRecords", datarows.size())

        def failed = 0
        def counter = 0
        def failedRecordsfile = new File("failedRecords" + new Date().date + new Date().time + ".csv")
        def failedRecords = failedRecordsfile.newWriter()


                headers.eachWithIndex {heading, j ->
                    if(j == (totalHeaders - 1)){
                        failedRecords.write(heading)
                    } else {
                        failedRecords.write(heading + "\t")
                    }
                }
        datarows.eachWithIndex {datarow, index ->
            def h = new GroovyHTTP("http://www.actuate.com/forms/user-submission/index-test.asp")//'http://salesforce.ringlead.com/cgi-bin/220/1/dedup.pl')
            h.setMethod('POST')
            println ''
            println '****URL Encoded Params***'
            headers.eachWithIndex { heading, i ->
                if(heading.toString().toLowerCase() != 'time submitted') {
                    h.setParam(URLEncoder.encode(heading.toString()),datarow[i].toString())
                    println URLEncoder.encode(heading.toString())+' : '+URLEncoder.encode(datarow[i].toString())
                }
            }
	        //h.setParam("source","Sourcemedia")
            h.open()
            h.write()
            h.read()
            println "****HEADER****"
            println h.headers[0]
            println "****CONTENT****"
            String content = h.getContent()
            println content
            if(content.contains('Success') || content.contains('Submitted')){
                counter++
            } else {
                failed++
                failedRecords.write("\n") 
                headers.eachWithIndex {heading, k ->
                    if(k == (totalHeaders - 1)){
                        failedRecords.write(datarow[k])
                    } else {
                        failedRecords.write(datarow[k] + ",")
                    }
                }
            }
            h.close()
        }
        failedRecords.close()
        map.put("totalRecordsProcessed", counter)
        map.put("totalRecordsFailed", failed )
        map.put("failedfile", failedRecordsfile.getPath())
         render(view: 'show', model: [map: map])
        
    }
}

