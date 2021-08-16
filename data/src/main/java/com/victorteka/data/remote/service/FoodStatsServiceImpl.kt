import com.victorteka.data.remote.RetrofitUtil.safeApiCall
import com.victorteka.data.remote.api.NutritionixApi
import com.victorteka.domain.Result
import com.victorteka.domain.models.FoodDetailsResponse
import com.victorteka.domain.models.SearchResponse
import com.victorteka.domain.services.FoodStatsService
import javax.inject.Inject

internal class FoodStatsServiceImpl @Inject constructor(
    private val nutritionixApi: NutritionixApi
) : FoodStatsService {
    override suspend fun searchFoodStats(queryTerm: String): Result<SearchResponse> = safeApiCall {
        nutritionixApi.searchFood(queryTerm)
    }

    override suspend fun queryFoodDetails(queryTerm: HashMap<String, String>): Result<FoodDetailsResponse> =
        safeApiCall {
            nutritionixApi.queryFoodDetails(queryTerm)
        }

}