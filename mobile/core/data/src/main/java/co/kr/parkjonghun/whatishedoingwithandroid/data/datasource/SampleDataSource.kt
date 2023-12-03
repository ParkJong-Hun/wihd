package co.kr.parkjonghun.whatishedoingwithandroid.data.datasource

import co.kr.parkjonghun.whatishedoingwithandroid.data.model.S1

// TODO: using flow to follow the UDF.
class SampleDataSource {
    fun getS1(): S1 {
        return S1(s = "sample data.")
    }
}
