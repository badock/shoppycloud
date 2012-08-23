
public class DomainFilters {
	def filters = {
		sampleFilter(controller:'subscription', action:'index', invert: true) {
			before = {
				if(request.store == -1) {
					redirect(controller:"subscription",action:"index")
				}

			}
		}
	}
}