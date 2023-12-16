package co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource

import co.kr.parkjonghun.whatishedoingwithandroid.outside.model.S1

// TODO: using flow to follow the UDF.
class SampleDataSource {
    fun getS1(): S1 {
        return S1(s = "sample data.")
    }
}
