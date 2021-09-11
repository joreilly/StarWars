import Foundation
import SwiftUI
import shared

class StarWarsViewModel: ObservableObject {
    let repository = StarWarsRepository()
    @Published public var filmList: [Film] = []
    
    func fetchFilms() {
        repository.getFilms { filmList in
            self.filmList = filmList
        }
    }

}

